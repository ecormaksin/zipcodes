path "secret/application" {
  capabilities = ["read"]
}

path "secret/application/local_vault" {
  capabilities = ["read"]
}

path "secret/zipcodes" {
  capabilities = ["read"]
}

path "secret/zipcodes/*" {
  capabilities = ["read"]
}
