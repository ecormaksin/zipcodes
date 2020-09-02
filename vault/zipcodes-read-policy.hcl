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

path "secret/data/application" {
  capabilities = ["read"]
}

path "secret/data/application/local_vault" {
  capabilities = ["read"]
}

path "secret/data/zipcodes" {
  capabilities = ["read"]
}

path "secret/data/zipcodes/*" {
  capabilities = ["read"]
}
