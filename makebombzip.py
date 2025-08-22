import os

def crear_archivo(nombre, tamaño_mb):
    
    tamaño_bytes = tamaño_mb * 1024 * 1024  
    
    with open(nombre, "wb") as f:
 
        f.write(b"\0" * tamaño_bytes)

    print(f"Archivo '{nombre}' creado con {tamaño_mb} MB.")

crear_archivo("archivo_100mb.bin",100) 