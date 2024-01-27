ALTER TABLE credit
    DROP CONSTRAINT pk_credit;

ALTER TABLE credit
    DROP COLUMN id;

ALTER TABLE credit
    ADD COLUMN id BIGINT;

ALTER TABLE credit
    ADD PRIMARY KEY (credit_code);