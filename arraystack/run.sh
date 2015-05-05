#!/bin/sh
rm -rf *.class
javac *.java
for i in 10  25  50  100  200  400  600  800 1000  1500  2000  4000  6000  8000 10000; do
    for j in 0; do
	java StackTest $i $j
	java StackTestSync $i $j
	java StackTestLock $i $j
    done
done
for i in 10  25  50  100  200  400  600  800 1000  1500  2000  4000  6000  8000 10000; do
    for j in 50; do
	java StackTest $i $j
	java StackTestSync $i $j
	java StackTestLock $i $j
    done
done
for i in 10  25  50  100  200  400  600  800 1000  1500  2000  4000  6000  8000 10000; do
    for j in 100; do
	java StackTest $i $j
	java StackTestSync $i $j
	java StackTestLock $i $j
    done
done

