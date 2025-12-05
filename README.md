# Advent of Code 2022

My solutions to [Advent of Code 2022](https://adventofcode.com/2022), in Kotlin.

## Aims

- declarative over imperative (what over how)
- readability over conciseness

## Structure

- `src/Day##.kt`: solutions to each day's problems
  - `fun main`: print the solutions
  - `fun part1`: solution to part 1
  - `fun part2`: solution to part 2
- `src/Day##.txt`: input file

## Build

```shell
./gradlew build
```

## Run

From your IDE, or:

```shell
kotlin -cp ./build/libs/advent-of-code-2022.jar Day##Kt
```
