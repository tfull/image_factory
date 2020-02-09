cd images
ppm_files=`ls ppm`

for file in $ppm_files
do
	if [ ! -f png/${file%.*}.png ]; then
		convert ppm/$file png/${file%.*}.png
	fi
done
