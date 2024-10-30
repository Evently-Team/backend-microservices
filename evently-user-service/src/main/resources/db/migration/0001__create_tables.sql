create table users (
    id uuid not null default gen_random_uuid(),
    name varchar(100) not null,
    username varchar(50) not null unique,
    role int not null,
    is_active boolean not null default true,
    created_at timestamp not null default now(),

    primary key (id)
);

create table profiles (
    id uuid not null default gen_random_uuid(),
    user_id uuid not null,
    cities varchar(50)[5] not null,
    description varchar(300) default null,
    pronouns varchar(6) default null,
    birthdate date default null,
    friends_count integer default 0,

    primary key (id),
    foreign key (user_id) references users(id)
);

create table credentials (
    id uuid not null default gen_random_uuid(),
    user_id uuid not null,
    email varchar(254) not null,
    password_fingerprint varchar(32) not null,

    primary key (id),
    foreign key (user_id) references users(id)
);

create table relationships (
    id uuid not null default gen_random_uuid(),
    user_id uuid not null,
    friend_id uuid not null,
    since timestamp not null default now(),

    primary key (id),
    foreign key (user_id) references users(id),
    foreign key (friend_id) references users(id)
);

