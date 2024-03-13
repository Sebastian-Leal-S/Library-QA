const cuadro = document.getElementById('cube');
const buttonChangeColor = document.getElementById('button-change-color');
const inputTexto = document.getElementById('input-text');
const buttonChangeText = document.getElementById('button-change-text');
const textoMostrado = document.getElementById('text-visible');
const contador = document.getElementById('contador');

function colorAleatorio() {
    return '#' + Math.floor(Math.random() * 16777215).toString(16);
}

function cambiarColor() {
    const colorFondo = colorAleatorio();
    const luminosidadFondo = obtenerLuminosidad(colorFondo);
    const colorTexto = luminosidadFondo > 0.5 ? '#000000' : '#ffffff'; // Cambiar el color del texto según la luminosidad del fondo

    cuadro.style.backgroundColor = colorFondo;
    document.getElementById('cantidad-clicks').style.color = colorTexto;
}

function obtenerLuminosidad(color) {
    const rgb = hexToRgb(color);
    return (0.2126 * rgb.r + 0.7152 * rgb.g + 0.0722 * rgb.b) / 255;
}

function hexToRgb(hex) {
    const bigint = parseInt(hex.slice(1), 16);
    const r = (bigint >> 16) & 255;
    const g = (bigint >> 8) & 255;
    const b = bigint & 255;
    return { r, g, b };
}

function mostrarTexto() {
    const texto = inputTexto.value.trim();
    if (texto !== '') {
        textoMostrado.textContent = texto;
    }
}

let count = 0;

function aumentarContador() {
    count++;
    contador.textContent = count;
}

buttonChangeText.addEventListener('click', mostrarTexto);
buttonChangeColor.addEventListener('click', cambiarColor);
buttonChangeColor.addEventListener('click', aumentarContador);