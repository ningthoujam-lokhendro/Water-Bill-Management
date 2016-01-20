#!/bin/bash

user='root'
passwd='system'
db='wbmdb'

mysql --user=$user --password=$passwd --database=$db < sp_generate_meter_reading.sql
mysql --user=$user --password=$passwd --database=$db < sp_generate_meter_reading_temp.sql
mysql --user=$user --password=$passwd --database=$db < sp_generate_due.sql
mysql --user=$user --password=$passwd --database=$db < sp_generate_bill.sql

echo 'DONE'
 
