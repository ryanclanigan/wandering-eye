new_zip=$(ls /home/ubuntu/fin | grep wandering)

if [ -z "$new_zip" ]; then
  exit 1
fi

unzip -d /home/ubuntu/fin/wandering /home/ubuntu/fin/wandering-eye.zip

rm -r /home/ubuntu/wandering-eye
mv /home/ubuntu/fin/wandering/wandering-eye /home/ubuntu/
mv /home/ubuntu/fin/wandering.zip /home/ubuntu/
rm -r /home/ubuntu/fin/**

pkill java

nohup /home/ubuntu/wandering-eye/bin/wandering-eye &
