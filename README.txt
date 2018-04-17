---- Team D Gift Request ----

Welcome to the Dapper Demogorgon's Gift Request API, for requesting a gift from the gift shop to any location in the hospital!

----------------------------------------------------------------------------------------------
Steps to include this API into your project:

1) Add the JAR file as a library in your project
2) Add a function that is linked to a button in your UI
3) In that function, enter the following code:

GiftServiceRequest giftServiceRequest = new GiftServiceRequest();
try{
    giftServiceRequest.run(0, 0, 1900, 1000, null, null, null);
}catch (Exception e){
    System.out.println("Failed to run API");
    e.printStackTrace();
}

And that's it, the API should just load the next time the function is called by the button!

ALTERNATIVE GRADLE INSTALLATION -------------------------------------------------------------

1) In your build.gradle file, add the line "compile fileTree(dir: 'libs', include: '*.jar')" (without surrounding double quotes) after the line "dependencies {"
2) Create a folder called "libs" at the top of your project file tree
3) Place Team-D-Gift-Request.jar in your libs folder, and now gradle should take care of installing everything in the libs folder!

----------------------------------------------------------------------------------------------

About the run function:
1) the first 2 parameters are the x and y coordinates of the top left corner of the window
2) the next 2 parameters are the height and the width of the window created
	HINT: The suggested minimum height is 1000 and the suggested minimum width is 1900
3) the next parameter is the path to your CSV file, if this is NULL, we have provided a default
4) the next parameter is the destination node. This is not terribly useful, but if a String is given the default selected location for the destination
	of the delivery will be the given String
5) the last parameter is not used

----------------------------------------------------------------------------------------------


OPTIONAL [but highly useful] EXTRA COMMANDS ==================================================

**Disable admin options**:
- to disable admin options (could be used to disable admin options when non-admins are logged in), insert the following code before giftServiceRequest.run()

	giftServiceRequest.disableAdmin();

**Load a list of available locations**:
- to load a list of locations to select as the delivery destination, insert the following code before giftServiceRequest.run()

	giftServiceRequest.importLocations(List<String> locations);

- where locations is a list of Strings representing the names of potential locations. This will give autocomplete suggestions in the text field
	for selecting a delivery destination.

**Reset the Gift Request database**:
- if for some reason during developement you want to whipe the database, just run

	giftServiceRequest.resetDatabase();

==============================================================================================


FULL EXAMPLE:

GiftServiceRequest giftServiceRequest = new GiftServiceRequest();
giftServiceRequest.disableAdmin(); // disables admin options
List<String> locations = new ArrayList<>();
locations.add("Endoscopy");
locations.add("Garden Cafe");
giftServiceRequest.importLocations(locations); // loads autocomplete locations
try{
    giftServiceRequest.run(0, 0, 1900, 1000, null, null, null);
}catch (Exception e){
    System.out.println("Failed to run API");
    e.printStackTrace();
}

==============================================================================================

