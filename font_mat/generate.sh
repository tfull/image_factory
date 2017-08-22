code=Main.c
program=main
max_length=24
directory=images
gcc -o $program $code
./$program
mkdir -p $directory
for color in black white
do
    convert ${color}.ppm ${color}.png
    for n in `seq $max_length`
    do
        body=`yes ${color}.png | head -${n}`
        convert +append $body $directory/${color}_${n}.png
    done
done
rm black.ppm white.ppm black.png white.png $program
