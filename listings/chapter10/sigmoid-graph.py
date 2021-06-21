import matplotlib.pyplot as plt
import numpy as np
import math

x = np.linspace(-10, 10, 1000)
y = []
for x_i in x:
    y.append(1 / (1 + math.exp(-x_i)))

plt.plot(x, y, color='red')
plt.xlabel('x')
plt.ylabel('y')
plt.grid()
plt.axhline(linewidth=2)
plt.axvline(linewidth=2)
plt.show()
