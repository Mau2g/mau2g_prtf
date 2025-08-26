import os
import string

def crear_archivo(nombre, tamaño_mb):
    tamaño_bytes = tamaño_mb * 1024 * 1024
    alfanum = (string.ascii_letters + string.digits).encode('ascii')
    n = len(alfanum)

    with open(nombre, "wb") as f:
        for _ in range(tamaño_bytes):
            f.write(alfanum[os.urandom(1)[0] % n:n % n + 1])

    print(f"Archivo '{nombre}' creado con {tamaño_mb} MB de caracteres alfanuméricos.")

crear_archivo("archivo_100mb.bin", 100)