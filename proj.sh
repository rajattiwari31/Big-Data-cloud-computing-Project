cd zookeeper-3.4.9/zookeeper-3.4.9A
javac ZKExists.java
java ZKExists >exists.txt
javac server.java
var=$(tail -n1 exists.txt)
if [ "$var" = 0 ]; then
	echo "INITIALIZING ...."
	javac ZKCreate.java
	java ZKCreate
	var=$(tail -n1 META_ip.txt)
	echo $var>master_ip.txt
	(gnome-terminal --command "java server $var true" &) && sleep 0.8 && xdotool windowminimize $(xdotool search --class 'gnome-terminal' |sort|tail -1)
	javac ZKCreate1.java
	java ZKCreate1
	var=$(tail -n2 META_ip.txt | head -n1)
	(gnome-terminal --command "java server $var false" &) && sleep 0.8 && xdotool windowminimize $(xdotool search --class 'gnome-terminal' |sort|tail -1)
	var=$(tail -n1 META_ip.txt)
	(gnome-terminal --command "java server $var false" &) && sleep 0.8 && xdotool windowminimize $(xdotool search --class 'gnome-terminal' |sort|tail -1)
	javac ZKCreate2.java
	java ZKCreate2
	var=$(tail -n2 META_ip.txt | head -n1)
	(gnome-terminal --command "java server $var false" &) && sleep 0.8 && xdotool windowminimize $(xdotool search --class 'gnome-terminal' |sort|tail -1)
	var=$(tail -n1 META_ip.txt)
	(gnome-terminal --command "java server $var false" &) && sleep 0.8 && xdotool windowminimize $(xdotool search --class 'gnome-terminal' |sort|tail -1)
	echo "MASTER CREATED"
else
	echo "MASTER EXISTS"
	head -n1 META.txt > temp.txt
	rm META.txt
	mv temp.txt META.txt
	var=$(tail -n1 master_ip.txt)
		(gnome-terminal --command "java server $var true" &) && sleep 0.8 && xdotool windowminimize $(xdotool search --class 'gnome-terminal' |sort|tail -1)
	javac ZKCreate1.java
	java ZKCreate1
	var=$(tail -n2 META_ip.txt | head -n1)
		(gnome-terminal --command "java server $var false" &) && sleep 0.8 && xdotool windowminimize $(xdotool search --class 'gnome-terminal' |sort|tail -1)
	var=$(tail -n1 META_ip.txt)
		(gnome-terminal --command "java server $var false" &) && sleep 0.8 && xdotool windowminimize $(xdotool search --class 'gnome-terminal' |sort|tail -1)
	javac ZKCreate2.java
	java ZKCreate2
	var=$(tail -n2 META_ip.txt | head -n1)
		(gnome-terminal --command "java server $var false" &) && sleep 0.8 && xdotool windowminimize $(xdotool search --class 'gnome-terminal' |sort|tail -1)
	var=$(tail -n1 META_ip.txt)
		(gnome-terminal --command "java server $var false" &) && sleep 0.8 && xdotool windowminimize $(xdotool search --class 'gnome-terminal' |sort|tail -1)
	
fi
echo "INITIALIZING CLIENT ...."
var=$(tail -n1 master_ip.txt)
num_var=$(wc -l < META.txt)
javac client.java
java client $var 6000 $num_var






