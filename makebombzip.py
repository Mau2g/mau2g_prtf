import os
import string

def crear_archivo(tamaño_mb):
    nombre = f"ArchData_{tamaño_mb}MB.bin"  # nombre automático
    tamaño_bytes = tamaño_mb * 1024 * 1024
    alfanum = (string.ascii_letters + string.digits).encode('ascii')
    n = len(alfanum)

    with open(nombre, "wb") as f:
        for _ in range(tamaño_bytes):
            f.write(alfanum[os.urandom(1)[0] % n:n % n + 1])

    print(f"Archivo '{nombre}' creado con {tamaño_mb} MB de caracteres alfanuméricos.")


if __name__ == "__main__":
    tamaño_mb = int(input("Ingrese el tamaño en MB del archivo: "))
    crear_archivo(tamaño_mb)