# ps
Xml read and write , automatic .


The main class will let u establish the input and output folders . Copy xml files that u need to parse into the input folder then run the program. If files had an inccorect name it will be listed in the terminal console. 

As an adition library u have it in the project, lib folder, it's Dom Parser , which you will have to import as external library.

==============================================

Problem description

SACOM is an electronic retailer that has multiple online and physical stores. SACOM needs a middleware application that can integrate the existing different ordering systems with the suppliers of electronic products. 
SACOM customers place product orders from various locations and systems and these orders need to be processed and sent to the corresponding suppliers. 
The orders are sent as XML files on a central server, where your application needs to process them and create the XML files that will be sent to the suppliers. 

Each XML file that contains orders will be processed into multiple XML files, one file per each supplier. The supplier output file will contain all the supplier’s products from one orders file, sorted descendant by the timestamp when the order was created and product price. Each product that will be sent to the supplier must also contain the order ID, so that it will be easily tracked to the original order.
The application will wait for the orders files in one folder and will export the supplier files in a separate folder. Once a file that contains orders is received in the specific folder, the application will process it, and then it will wait for another orders file to process.

Once a customer places an order, he expects it to be delivered as soon as possible, so you must focus on the application XML files processing performance in order to keep the customers happy.
Orders input file
The orders file name pattern is orders##.xml, where ## are digits. For example orders01.xml is a valid name and ordersss.xml is not. 


The suppliers file name pattern is suppliername##.xml, where ## are the digits from the orders xml file processed. For example, after processing the orders23.xml that contains suppliers Apple, Sony and Panasonic, the program must create the suppliers files with the following names: Apple23.xml, Sony23.xml and Panasonic23.xml. 

After processing input file orders23.xml, the application must create three suppliers files: Apple23.xml, Panasonic23.xml and Sony23.xml. 
