from flask import Flask, jsonify

app = Flask(__name__)

# Simulación de una base de datos
usuarios = {
    1: {"nombre": "Carlos", "objetivo": "ganar_musculo"},
    2: {"nombre": "Ana", "objetivo": "perder_grasa"},
    3: {"nombre": "Luis", "objetivo": "mantener"}
}

@app.route('/usuario/<int:id>', methods=['GET'])
def obtener_usuario(id):
    usuario = usuarios.get(id)
    if usuario:
        return jsonify(usuario)
    else:
        return jsonify({"error": "Usuario no encontrado"}), 404

if __name__ == '__main__':
    app.run(port=5001, debug=True)
