from flask import Flask, jsonify
import requests

app = Flask(__name__)

rutinas = {
    "ganar_musculo": [
        "Press banca - 4x8",
        "Sentadilla - 4x10"
    ],
    "perder_grasa": [
        "HIIT - 20 minutos",
        "Burpees - 3x15"
    ],
    "mantener": [
        "Fullbody - 3 días",
        "Caminata rápida - 40 minutos"
    ]
}

@app.route('/rutina/<int:id_usuario>', methods=['GET'])
def obtener_rutina(id_usuario):
    try:
        # Llamada al microservicio de usuarios
        respuesta = requests.get(f'http://localhost:5001/usuario/{id_usuario}')
        if respuesta.status_code == 200:
            usuario = respuesta.json()
            objetivo = usuario.get("objetivo")
            ejercicios = rutinas.get(objetivo, [])
            return jsonify({
                "usuario": usuario["nombre"],
                "objetivo": objetivo,
                "rutina": ejercicios
            })
        else:
            return jsonify({"error": "Usuario no encontrado"}), 404
    except Exception as e:
        return jsonify({"error": "Error al contactar el microservicio de usuarios"}), 500

if __name__ == '__main__':
    app.run(port=5002, debug=True)
