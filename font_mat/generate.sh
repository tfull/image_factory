code=Main.c
program=main
length=16
directory=images
gcc -o $program $code
./$program
mkdir -p $directory
for color in black white
do
    convert ${color}.ppm ${color}.png
    convert ${color}_space.ppm ${color}_space.png
    body_even=`yes ${color}.png | head -${length}`
    convert +append $body_even $directory/${color}_${length}.png
    body_odd=`yes ${color}.png | head -$((length - 1))`
    convert +append ${color}_space.png $body_odd ${color}_space.png $directory/${color}_$((length - 1)).png
    rm ${color}.png ${color}_space.png ${color}.ppm ${color}_space.ppm
done
rm $program
