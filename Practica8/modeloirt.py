import matplotlib.pyplot as plt
import numpy as np
from scipy.special import expit  # Función logística

# Valores de θ (nivel del rasgo latente)
theta = np.linspace(-4, 4, 100)

# Parámetros de discriminación
a_values = [0.5, 1, 2]
colors = ['red', 'black', 'blue']
markers = ['s', '^', 'D']
labels = [r'$a_i = 0.5$', r'$a_i = 1$', r'$a_i = 2$']

# Crear la figura
plt.figure(figsize=(10, 6))

# Dibujar las curvas para cada valor de a
for a, color, marker, label in zip(a_values, colors, markers, labels):
    p_theta = expit(a * theta)  # Función logística
    plt.plot(theta, p_theta, label=label, color=color, marker=marker, markevery=15)

# Líneas horizontales auxiliares
plt.axhline(0.5, color='gray', linestyle='--')
plt.axhline(0.8, color='gray', linestyle='--')
plt.axhline(0.2, color='gray', linestyle='--')

# Etiquetas en español
plt.xlabel('Nivel del rasgo latente (θ)', fontsize=12)
plt.ylabel('Probabilidad de respuesta correcta (P(θ))', fontsize=12)
plt.title('Curvas características del ítem (ICC) para diferentes valores de $a_i$', fontsize=14)
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()
