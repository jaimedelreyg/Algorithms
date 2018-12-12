#!/bin/bash

#Eliminar archivos de compilacion anteriores y txt de log
rm bin/Dijkstra_Algorithm/*.class
rm Tests/Test*
rm *.txt
rm Imagenes/Grafica.png
echo "---->Ficheros eliminados"
#Compilar proyecto
javac -d bin/ src/Dijkstra_Algorithm/*.java
echo "---->Proyecto compilado"
#Ejecutar generador de casos y casos particulares
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test1.txt 100 100
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test2.txt 200 100
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test3.txt 300 100
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test4.txt 400 100
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test5.txt 500 100
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test6.txt 600 100
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test7.txt 700 100
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test7.txt 800 100
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test7.txt 900 100
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test8.txt 2000 100
java -cp bin/ Dijkstra_Algorithm.ExampleGenerator Tests/Test9.txt 5000 100



echo "----> Ficheros de prueba creados"
#ejecutar algoritmo

java -cp bin/ Dijkstra_Algorithm.Main Tests/IniTest1.txt 1000
java -cp bin/ Dijkstra_Algorithm.Main Tests/IniTest2.txt 1000
java -cp bin/ Dijkstra_Algorithm.Main Tests/Test1.txt 1000
java -cp bin/ Dijkstra_Algorithm.Main Tests/Test2.txt 1000
java -cp bin/ Dijkstra_Algorithm.Main Tests/Test3.txt 1000
java -cp bin/ Dijkstra_Algorithm.Main Tests/Test4.txt 1000
java -cp bin/ Dijkstra_Algorithm.Main Tests/Test5.txt 1000
java -cp bin/ Dijkstra_Algorithm.Main Tests/Test6.txt 1000
java -cp bin/ Dijkstra_Algorithm.Main Tests/Test7.txt 1000
java -cp bin/ Dijkstra_Algorithm.Main Tests/Test8.txt 1000
java -cp bin/ Dijkstra_Algorithm.Main Tests/Test9.txt 1000

echo "----> Algoritmo ejecutado"
#ejecutar gnuplot para graficaas y guardarlas modo png

cat << _EOF | gnuplot
	set terminal pngcairo
	set output 'Imagenes/Grafica.png'
	set title "Tiempos de ejecucion del algoritmo de Dijkstra"
	set ylabel "ms"
	set xlabel "nodos"
	set multiplot
	plot 'ArrayResults.txt' w l, 'HeapResults.txt' w l
_EOF
echo "----> Graficas creadas"


