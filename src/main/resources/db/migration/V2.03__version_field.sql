ALTER TABLE part
    ADD COLUMN version INT UNSIGNED NOT NULL
        AFTER id;