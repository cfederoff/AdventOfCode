from z3 import *

    sum = 0
    with open('input.txt', 'r') as file:
        for i in file:
            schematics = []
            str = i
            joltagesStr = []
            joltage = ""
            checking = 1
            nums = []
            for x in str:
                if x == '(':
                   checking = 2
                elif x == '{':
                    checking = 3
                elif x == '}':
                    joltagesStr.append(joltage)
                    checking = 4
                elif x == ')':
                    schematics.append(nums)
                    nums = []
                elif checking == 2 and x != ',' and x != ' ':
                    nums.append(int(x))
                elif checking == 3 and x != ',':
                    joltage += x
                elif checking == 3 and x == ',':
                    joltagesStr.append(joltage)
                    joltage = ""
            opt = Optimize()
            var = []
            for i in range(len(schematics)):
                var.append(Int(f'x{i}'))
            for b in var:
                opt.add(b >= 0)
            for i, x in enumerate(joltagesStr):
                vars = []
                for j, y in enumerate(schematics):
                    if (i in y):
                        vars.append(var[j])
                opt.add(Sum(vars) == x)
    # 4. Add an objective function to minimize
    # The handle 'h' is used to retrieve the value later
    # 5. Check for an optimal solution
            opt.minimize(Sum(var))
            if opt.check() == sat:
                m = opt.model()
                for i in m:
                    sum += m[i].as_long()
            else:
                print("Optimization failed or is unsatisfiable")
    print(sum)