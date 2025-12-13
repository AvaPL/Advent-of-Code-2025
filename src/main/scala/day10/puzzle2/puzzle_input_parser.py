import re

from package import Button, Machine


def parsed_input(string):
    return [parse(line) for line in string.split('\n')]


def parse(string):
    buttons = []
    for match in re.findall(r'\((.*?)\)', string):
        indices = {int(i) for i in match.split(',')}
        buttons.append(Button(indices))
    joltages = [int(joltage) for joltage in re.search(r'\{(.*?)\}', string).group(1).split(',')]
    return Machine(buttons, joltages)
