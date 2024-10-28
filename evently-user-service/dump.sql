create table users (
    id uuid not null,
    name varchar(255) not null,
    username varchar(255) not null unique,
    city varchar(255) not null,
    role varchar(255) not null default 'USER',
    created_at timestamp,

    primary key (id)
);

create table user_profiles (
    id uuid not null,
    user_id uuid not null,
    description varchar(255) default null,
    pronouns varchar(255) default null,
    birthdate date default null,
    friends_count integer default 0,

    primary key (id),
    foreign key (user_id) references users(id)
);

create table user_credentials (
    id uuid not null,
    user_id uuid not null,
    email varchar(255) not null,
    password_fingerprint varchar(255) not null,

    primary key (id),
    foreign key (user_id) references users(id)
);

create table friends (
    id uuid not null,
    user_id uuid not null,
    friend_id uuid not null,

    primary key (id),
    foreign key (user_id) references users(id),
    foreign key (friend_id) references users(id)
);
