function guardarNota() {
    const texto = document.getElementById('nota').value;
    if (texto.trim()) {
      let notas = JSON.parse(localStorage.getItem('notas') || '[]');
      notas.push(texto);
      localStorage.setItem('notas', JSON.stringify(notas));
      document.getElementById('nota').value = '';
      mostrarNotas();
    }
  }
  
  function mostrarNotas() {
    const notas = JSON.parse(localStorage.getItem('notas') || '[]');
    const lista = document.getElementById('listaNotas');
    lista.innerHTML = '';
    notas.forEach(nota => {
      const li = document.createElement('li');
      li.textContent = nota;
      lista.appendChild(li);
    });
  }
  
  mostrarNotas();
  