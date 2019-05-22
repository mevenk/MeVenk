

#!/bin/bash


export mevenkWebAppLogsDirectoryLoaction="file:////home/vkolisetty/RABOTA//MeVenk/WebApp/Logs/"

export mevenkWebAppLog4jSocketHost="VENKATESH-NUC"

export mevenkWebAppLog4jSocketPort="9898"

export mevenkWebappPropertiesFileSource="file:////home/vkolisetty/RABOTA/MeVenk/WebApp/sources/properties/mevenkwebapp.properties"

export mevenkWebappBasePropertiesFileSource="file:////home/vkolisetty/RABOTA/MeVenk/WebApp/sources/properties/mevenkwebappBase.properties"

export mevenkWebappDatabasePropertiesFileSource="file:////home/vkolisetty/RABOTA/MeVenk/WebApp/sources/properties/mevenkwebappDatabase.properties"


###########################################################################################################################################################
###########################################################################################################################################################

## Banner

export ENV_BANNER_CURRENT_DATE=`date`
export ENV_BANNER_HOSTNAME=`hostname`
export ENV_BANNER_NETWORK=`hostname -i`
export ENV_BANNER_IP_ADDRESS=`hostname -I`


###########################################################################################################################################################
###########################################################################################################################################################
###########################################################################################################################################################
###########################################################################################################################################################
###########################################################################################################################################################



java -jar MeVenkWebApp.jar



###########################################################################################################################################################
###########################################################################################################################################################

