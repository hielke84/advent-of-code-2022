# Advent of Code 2022

My solutions to [Advent of Code 2022](https://adventofcode.com/2022), in Kotlin.

## Aims

- declarative over imperative (what over how)
- readability over conciseness

## Structure

- `src/day##`
  - `Day##.kt`: solutions to the day's problems
    - `fun main`: print the solutions
    - `fun part1`: solution to part 1
    - `fun part2`: solution to part 2
  - `input.txt`: input file

## Build

```shell
./gradlew build
```

## Run

From your IDE, or:

```shell
kotlin -cp ./build/libs/advent-of-code-2022.jar day##.Day##Kt
```
