create table evently_users (
    id uuid not null,
    name varchar(100) not null,
    username varchar(50) not null unique,
    city varchar(50)[5] not null,
    role int not null,
    created_at timestamp not null,

    primary key (id)
);

create table evently_profiles (
    id uuid not null,
    user_id uuid not null,
    description varchar(300) default null,
    pronouns varchar(6) default null,
    birthdate date default null,
    friends_count integer default 0,

    primary key (id),
    foreign key (user_id) references users(id)
);

create table evently_credentials (
    id uuid not null,
    user_id uuid not null,
    email varchar(254) not null,
    password_fingerprint varchar(32) not null,

    primary key (id),
    foreign key (user_id) references users(id)
);

create table evently_relationships (
    id uuid not null,
    user_id uuid not null,
    friend_id uuid not null,
    since timestamp not null,

    primary key (id),
    foreign key (user_id) references users(id),
    foreign key (friend_id) references users(id)
);

