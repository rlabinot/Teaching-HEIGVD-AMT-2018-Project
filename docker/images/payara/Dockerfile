FROM payara/server-full:5.183

MAINTAINER Dejvid Muaremi <dejvid.muaremi@heig-vd.ch>

ADD drivers/mysql-connector-java-5.1.47.jar /opt/payara5/glassfish/domains/domain1/lib/

RUN echo "AS_ADMIN_PASSWORD=admin" > /tmp/gfpw.txt && \
  ./glassfish/bin/asadmin start-domain domain1 && \
  ./glassfish/bin/asadmin --interactive=false --user admin --passwordfile=/tmp/gfpw.txt create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --property user=stackoveramt:password=stackoveramt:url=\"jdbc:mysql://db:3306/stackoveramt\":useSSL=false:allowPublicKeyRetrieval=true:serverTimeZone=Europe/Paris stackoveramt && \
  ./glassfish/bin/asadmin --interactive=false --user admin --passwordfile=/tmp/gfpw.txt create-jdbc-resource --connectionpoolid stackoveramt jdbc/stackoveramt
