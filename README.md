# JCompress - OOAD File Processor

A flexible command-line file processing tool developed in Java. This project demonstrates the application of Object-Oriented Analysis and Design (OOAD) principles, specifically utilizing the **Observer**, **Strategy**, and **Decorator** design patterns.

## 🚀 Features

This tool allows users to perform multiple operations on a single file simultaneously through command-line arguments:
- **Compression**: Compress files using the ZIP algorithm (`-zip`).
- **Encryption**: Encrypt files using the DES algorithm (`-DES`).
- **Checksum Calculation**: Verify file integrity using the MD5 hashing algorithm (`-MD5`).

The core advantage of this architecture is that the source file is read only once (in chunks), and all selected operations are processed concurrently.

## 📐 Architecture & Design Patterns

1. **Observer Pattern**
   - **Subject**: `FilePublisher` reads the source file in 4KB chunks and broadcasts the data (`notify`) to all registered observers.
   - **Observers**: `CompressProcessor`, `EncryptProcessor`, and `ChecksumProcessor` subscribe to the publisher based on the user's command-line inputs. They process the incoming byte chunks in real-time.

2. **Strategy Pattern**
   - Algorithms are encapsulated into distinct classes (`ZipAlgorithm`, `DESAlgorithm`, `MD5Algorithm`) that implement common interfaces (`CompressStrategy`, `EncryptStrategy`, `ChecksumStrategy`).
   - This makes the system highly extensible. Adding a new algorithm (e.g., AES encryption or SHA-256 checksum) requires creating a new class without modifying the core processor logic.

3. **Decorator Pattern**
   - Implemented via Java's native I/O Streams. The processors dynamically wrap standard `FileOutputStream` with decorator streams like `ZipOutputStream` or `CipherOutputStream` to modify the output behavior on the fly.

## 💻 Usage

You can run the `jcompress` tool via the command line by providing the target file and the desired operation flags. The order of the flags does not matter, and you can combine them as needed.

**Command Syntax:**
```bash
javac -d out -sourcepath src src/th/ac/tu/jcompress/Main.java
java -cp out th.ac.tu.jcompress.Main <target_file> [-zip] [-DES] [-MD5]
```

## 📁 Project Structure

```text
src/th/ac/tu/jcompress/
├── Main.java                  # CLI entry point and argument parsing
├── core/
│   ├── FilePublisher.java     # The Subject (reads file and notifies)
│   └── FileObserver.java      # The Observer interface
├── processor/
│   ├── CompressProcessor.java # Handles compression stream
│   ├── EncryptProcessor.java  # Handles encryption stream
│   └── ChecksumProcessor.java # Handles hashing computation
└── strategy/
    ├── CompressStrategy.java  
    ├── ZipAlgorithm.java      
    ├── EncryptStrategy.java   
    ├── DESAlgorithm.java      
    ├── ChecksumStrategy.java  
    └── MD5Algorithm.java