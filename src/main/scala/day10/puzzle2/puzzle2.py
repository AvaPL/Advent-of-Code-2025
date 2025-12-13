from z3 import *

from puzzle_input_parser import parsed_input

from pathlib import Path


def fewest_button_presses(machine):
    """
    Convert a single machine into an Ax = b matrix equation and solve it with the Z3 optimizer.

    Example:

        Input:
        [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}

        Vector representation:
        a * [0 0 0 1] + b * [0 1 0 1] + c * [0 0 1 0] + d * [0 0 1 1] + e * [1 0 1 0] + f * [1 1 0 0] = [3 5 4 7]

        Matrix representation:
                                    [a]
        [ 0 | 0 | 0 | 0 | 1 | 1 ]   [b]   [3]
        [ 0 | 1 | 0 | 0 | 0 | 1 ] * [c] = [5]
        [ 0 | 0 | 1 | 1 | 1 | 0 ]   [d]   [4]
        [ 1 | 1 | 0 | 1 | 0 | 0 ]   [e]   [7]
                                    [f]
    """
    opt = Optimize()

    # Matrix equation button_matrix * press_count = joltages
    buttons = machine.buttons
    joltages = machine.joltages
    button_matrix = build_button_matrix(buttons, joltages_count=len(joltages))
    press_count = [Int(f"press_count_{i}") for i, _ in enumerate(buttons)]
    for i, _ in enumerate(joltages):
        opt.add(
            Sum(button_matrix[i][j] * press_count[j] for j, _ in enumerate(buttons)) == joltages[i]
        )

    # Press count has to be non-negative
    for press_count_i in press_count:
        opt.add(press_count_i >= 0)

    # Minimize sum of all press counts
    opt.minimize(Sum(press_count))

    # Solve
    opt.check()
    model = opt.model()
    solution = [model[press_count_i].as_long() for press_count_i in press_count]
    return sum(solution)


def build_button_matrix(buttons, joltages_count):
    matrix = [[0 for _ in buttons] for _ in range(joltages_count)]
    for button_index, button in enumerate(buttons):
        for joltage_index in button.indices:
            matrix[joltage_index][button_index] = 1
    return matrix


if __name__ == '__main__':
    machines = parsed_input(Path("../../../resources/input/day10.txt").read_text())
    result = sum([fewest_button_presses(machine) for machine in machines])
    print(result)
