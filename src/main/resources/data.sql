CREATE TABLE IF NOT EXISTS speakers
(
    id         SERIAL NOT NULL,
    firstname  varchar NOT NULL,
    lastname   varchar NOT NULL,
    talkname   varchar NOT NULL,
    likes      int8 NULL,
    created    timestamp NULL,
    updated    timestamp NULL,

    CONSTRAINT speakers_pk PRIMARY KEY (id),
    CONSTRAINT talkname_unique UNIQUE (talkname)
);

CREATE TABLE IF NOT EXISTS history
(
    id         SERIAL NOT NULL,
    talkname   varchar NULL,
    likes      int8 NULL,
    status     varchar NULL,
    created    timestamp NULL,

    CONSTRAINT history_pk PRIMARY KEY (id)
);

DELETE FROM speakers;
INSERT INTO speakers (id, firstname, lastname, talkname, likes, created, updated) VALUES (1, 'John', 'Doe', 'Spring best practice',  0, now(), now());
DELETE FROM history;