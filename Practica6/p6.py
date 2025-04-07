from flask import Flask, request, render_template_string

app = Flask(__name__)

# Rutinas por objetivo
rutinas = {
    'ganar_musculo': [
        'Press de banca - 4x8',
        'Sentadilla - 4x10',
        'Peso muerto - 4x6',
        'Dominadas - 4xMax',
        'Remo con barra - 4x10'
    ],
    'perder_grasa': [
        'HIIT - 20 minutos',
        'Circuito de pesas - 3 rondas',
        'Burpees - 3x15',
        'Mountain climbers - 4x30 seg',
        'Cinta - 30 minutos'
    ],
    'mantener': [
        'Fullbody 3 días por semana',
        'Caminata rápida - 40 minutos',
        'Flexiones - 3x12',
        'Bicicleta estacionaria - 20 minutos',
        'Plancha - 3x1 minuto'
    ]
}

# Página de inicio con formulario
@app.route('/')
def inicio():
    return '''
        <html>
            <head>
                <title>Rutinas de GYM</title>
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background-color: #f7f9fc;
                        text-align: center;
                        padding: 60px;
                    }
                    h1 {
                        color: #2e7d32;
                    }
                    form {
                        margin-top: 40px;
                    }
                    select, button {
                        padding: 10px 20px;
                        margin: 10px;
                        font-size: 16px;
                        border: 1px solid #ccc;
                        border-radius: 5px;
                    }
                    button {
                        background-color: #2e7d32;
                        color: white;
                        cursor: pointer;
                        transition: background-color 0.3s;
                    }
                    button:hover {
                        background-color: #27672b;
                    }
                </style>
            </head>
            <body>
                <h1>Servicio de Rutinas de GYM</h1>
                <p>Selecciona tu objetivo para recibir una rutina personalizada:</p>
                <form action="/rutina" method="get">
                    <select name="objetivo">
                        <option value="ganar_musculo">Ganar Músculo</option>
                        <option value="perder_grasa">Perder Grasa</option>
                        <option value="mantener">Mantener</option>
                    </select>
                    <button type="submit">Ver Rutina</button>
                </form>
            </body>
        </html>
    '''

# Página con la rutina seleccionada
@app.route('/rutina')
def rutina():
    objetivo = request.args.get('objetivo')
    ejercicios = rutinas.get(objetivo)

    if not ejercicios:
        return '''
            <html>
                <body style="font-family: Arial; background-color: #ffe5e5; text-align: center; padding: 60px;">
                    <h2 style="color: #c62828;">Objetivo no válido</h2>
                    <p>Por favor selecciona un objetivo válido desde la página principal.</p>
                    <a href="/" style="color: #c62828;">Volver al inicio</a>
                </body>
            </html>
        ''', 400

    rutina_html = ''.join(f'<li>{ej}</li>' for ej in ejercicios)

    return render_template_string(f'''
        <html>
            <head>
                <title>Rutina - {objetivo.replace("_", " ").title()}</title>
                <style>
                    body {{
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background-color: #f0f4f7;
                        text-align: center;
                        padding: 60px;
                    }}
                    h2 {{
                        color: #1b5e20;
                    }}
                    ul {{
                        list-style: none;
                        padding: 0;
                        margin-top: 30px;
                    }}
                    li {{
                        background-color: #ffffff;
                        margin: 10px auto;
                        padding: 15px 20px;
                        max-width: 500px;
                        border-radius: 8px;
                        box-shadow: 0 2px 6px rgba(0,0,0,0.1);
                        font-size: 17px;
                    }}
                    a {{
                        display: inline-block;
                        margin-top: 30px;
                        color: #1b5e20;
                        text-decoration: none;
                        font-weight: bold;
                    }}
                </style>
            </head>
            <body>
                <h2>Rutina para: {objetivo.replace("_", " ").title()}</h2>
                <ul>
                    {rutina_html}
                </ul>
                <a href="/">← Volver al inicio</a>
            </body>
        </html>
    ''')

if __name__ == '__main__':
    app.run(debug=True, port=5000)
    
