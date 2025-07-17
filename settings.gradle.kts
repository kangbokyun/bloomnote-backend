rootProject.name = "bloomnote-backend"

include("domains")

include("modules")
include(":modules:bloomnote-jwt-module")
include(":modules:bloomnote-core-module")
include(":modules:bloomnote-redis-module")
include(":modules:bloomnote-database-module")
include(":modules:bloomnote-discovery-module")

include("services")
include(":services:bloomnote-test-service")
include(":services:bloomnote-user-service")
include(":services:bloomnote-gateway-service")
include(":services:bloomnote-discovery-service")
