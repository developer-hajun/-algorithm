class Node:
    live = True

    def __init__(self, p, n):
        self.prev = p if p >= 0 else None
        self.next = n if n < N else None


def solution(n, k, command):
    global N
    N = n
    table = {i: Node(i - 1, i + 1) for i in range(n)}

    now = k

    stack = []

    for cmd in command:
        if cmd[0] == 'C':
            table[now].live = False
            stack.append(now)
            prev, next = table[now].prev, table[now].next
            if prev is not None:
                table[prev].next = next

            if next is not None:
                table[next].prev = prev

            if table[now].next is None:
                now = table[now].prev
            else:
                now = table[now].next

        elif cmd[0] == 'Z':
            re = stack.pop()
            table[re].live = True

            prev, next = table[re].prev, table[re].next

            if prev is not None:
                table[prev].next = re

            if next is not None:
                table[next].prev = re


        else:
            c, amout = cmd.split()
            if c == 'U':
                for _ in range(int(amout)):
                    now = table[now].prev
            else:
                for _ in range(int(amout)):
                    now = table[now].next

    return ''.join('O' if table[i].live else 'X' for i in range(n))