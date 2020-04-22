package org.modernjavarecipes.parallelismAndConcurrency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoordinatingCompletableFuturesPart2Demo {
    public static void main(String[] args) {
        printGames(LocalDate.of(2017, Month.MAY, 5), 3);
    }

    //Main application code
    public static void printGames(LocalDate startDate, int days) {
        CompletableFuture<List<Result>> future =
                CompletableFuture.supplyAsync(
                        new GamePageLinksSupplier(startDate, days))
                        .thenApply(new BoxscoreRetriever());

        CompletableFuture<Void> futureWrite =
                future.thenAcceptAsync(this::saveResultList)
                        .exceptionally(ex -> {
                            System.err.println(ex.getMessage());
                            return null;
                        });

        CompletableFuture<OptionalInt> futureMaxScore =
                future.thenApplyAsync(this::getMaxScore);
        CompletableFuture<Optional<Result>> futureMaxGame =
                future.thenApplyAsync(this::getMaxGame);
        CompletableFuture<String> futureMax =
                futureMaxScore.thenCombineAsync(futureMaxGame,
                        (score, result) ->
                                String.format("Highest score: %d, Max Game: %s",
                                        score.orElse(0), result.orElse(null)));
        CompletableFuture.allOf(futureWrite, futureMax).join();
        future.join().forEach(System.out::println);
        System.out.println(futureMax.join());
    }

    //Save each box score to a file
    private static void saveResultList(List<Result> results) {
        results.parallelStream().forEach(this::saveResultToFile);
    }

    public static void saveResultToFile(Result result) {
        // ... determine a file name based on the date and team names ...
        try {
            File file = new File(dir + "/" + fileName);
            Files.write(file.toPath().toAbsolutePath(),
                    gson.toJson(result).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Getting the maximum total score and the game where it occurred
    private int getTotalScore(Result result) {
        // ... sum the scores of both teams ...
        return 0;
    }

    public OptionalInt getMaxScore(List<Result> results) {
        return results.stream()
                .mapToInt(this::getTotalScore)
                .max();
    }
    public Optional<Result> getMaxGame(List<Result> results) {
        return results.stream()
                .max(Comparator.comparingInt(this::getTotalScore));
    }

    //Get the game links for a range of dates
    public static class GamePageLinksSupplier implements Supplier<List<String>> {
        private static final String BASE ="http://gd2.mlb.com/components/game/mlb/";
        private LocalDate startDate;
        private int days;
        public GamePageLinksSupplier(LocalDate startDate, int days) {
            this.startDate = startDate;
            this.days = days;
        }

        public List<String> getGamePageLinks(LocalDate localDate) {
        // Use the JSoup library to parse the HTML web page and
        // extract the links that start with "gid"
            return Collections.emptyList();
        }

        @Override
        public List<String> get() {

            return Stream.iterate(startDate, d -> d.plusDays(1))
                    .limit(days)
                    .map(this::getGamePageLinks)
                    .flatMap(list -> list.isEmpty() ? Stream.empty() : list.stream())
                    .collect(Collectors.toList());
        }

    }

    class Result{
    }

    //Retrieving the list of box scores from the list of game links
    public static class BoxscoreRetriever implements Function<List<String>, List<Result>> {
        private static final String BASE = "http://gd2.mlb.com/components/game/mlb/";
        private OkHttpClient client = new OkHttpClient();
        private Gson gson = new Gson();

        @SuppressWarnings("ConstantConditions")
        public Optional<Result> gamePattern2Result(String pattern) {
            // ... code omitted ...
            String boxscoreUrl = BASE + dateUrl + pattern + "boxscore.json";
            // .. set up OkHttp to make the network call ...
            try {
                // ... get the response ...
                if (!response.isSuccessful()) {
                    System.out.println("Box score not found for " + boxscoreUrl);
                    return Optional.empty();
                }
                return Optional.ofNullable(
                        gson.fromJson(response.body().charStream(), Result.class));
            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        @Override
        public List<Result> apply(List<String> strings) {
            return strings.parallelStream()
                    .map(this::gamePattern2Result)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }
}
