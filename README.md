# Zura's Super Project


## Database Script

```sql
create database ZurasPosts;

use ZurasPosts;

create table Posts(
    Id int primary key auto_increment,
    Header varchar(30) not null,
    Body varchar(500) not null,
    Author varchar(50) not null,
    CreatedAt datetime not null default now()
)
```

glassfish 6.2.5 is required to run project.
