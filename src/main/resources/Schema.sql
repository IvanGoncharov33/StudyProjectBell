CREATE TABLE IF NOT EXISTS Organization(
id                INTEGER  AUTO_INCREMENT     COMMENT 'Уникальный идентификатор организации' ,
version           INTEGER         NOT NULL    COMMENT 'Служебное поле Hibernate',
name              VARCHAR(255)    NOT NULL    COMMENT 'Наименование организации',
full_name         VARCHAR(255)    NOT NULL    COMMENT 'Полное наименование организации',
inn               VARCHAR(10)     NOT NULL    COMMENT 'ИНН организации',
kpp               VARCHAR(9)      NOT NULL    COMMENT 'КПП организации',
phone             VARCHAR(11)                 COMMENT 'Телефонный номер организации',
address           VARCHAR(255)    NOT NULL    COMMENT 'Юридический адрес организации',
is_active         BOOLEAN                     COMMENT 'Действующая ли организация',

CONSTRAINT org_id_pk PRIMARY KEY (id),
CONSTRAINT org_inn_unique UNIQUE (inn),
CONSTRAINT org_kpp_unique UNIQUE (kpp)
);
COMMENT ON TABLE organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
id                INTEGER  AUTO_INCREMENT     COMMENT 'Уникальный идентификатор офиса' ,
version           INTEGER        NOT NULL     COMMENT 'Служебное поле Hibernate',
organization_id   INTEGER        NOT NULL     COMMENT 'Идентификатор организации',
name              VARCHAR(255)                COMMENT 'Наименование офиса',
phone             VARCHAR(11)                 COMMENT 'Телефонный номер офиса',
address           VARCHAR(255)                COMMENT 'Адрес офиса',
is_active         BOOLEAN                     COMMENT 'Рабочий ли офис',

CONSTRAINT office_id_pk PRIMARY KEY (id)
);
COMMENT ON TABLE office IS 'Офисы организаций';

CREATE TABLE IF NOT EXISTS Employee(
id                INTEGER  AUTO_INCREMENT     COMMENT 'Уникальный идентификатор сотрудника',
version           INTEGER       NOT NULL      COMMENT 'Служебное поле Hibernate',
office_id         INTEGER       NOT NULL      COMMENT 'Идентификатор офисов',
first_name        VARCHAR(255)  NOT NULL      COMMENT 'Имя сотрудника',
second_name       VARCHAR(255)                COMMENT 'Фамилия сотрудника',
middle_name       VARCHAR(255)                COMMENT 'Отчество сотрудника',
position          VARCHAR(255)  NOT NULL      COMMENT 'Должность сотрудника',
citizenship_id    INTEGER                     COMMENT 'Идентификатор гражданства сотрудника',
phone             VARCHAR(11)                 COMMENT 'Телефонный номер сотрудника',
is_identified     BOOLEAN                     COMMENT 'Идентифицированный ли сотрудник',
CONSTRAINT emp_id_pk PRIMARY KEY (id)
);
COMMENT ON TABLE employee IS 'Сотрудники офисов';

CREATE TABLE IF NOT EXISTS Document(
employee_id       INTEGER       NOT NULL      COMMENT 'Уникальный идентификатор работника' ,
version           INTEGER       NOT NULL      COMMENT 'Служебное поле Hibernate',
document_number   VARCHAR(255)                COMMENT 'Номер документа',
document_date     VARCHAR(255)                COMMENT 'Дата выдачи документа',
document_type_id  INTEGER                     COMMENT 'Идентификатор вида документа',
CONSTRAINT doc_id_pk PRIMARY KEY  (employee_id)
);
COMMENT ON TABLE document IS 'Документы удостоверяющие личность физического лица';

CREATE TABLE IF NOT EXISTS Document_type(
id                INTEGER  AUTO_INCREMENT     COMMENT 'Уникальный идентификатор',
name              VARCHAR(255)  NOT NULL      COMMENT 'Наименование документа',
code              VARCHAR(2)    NOT NULL      COMMENT 'Код документа' ,
CONSTRAINT doc_type_id_pk PRIMARY KEY (id),
CONSTRAINT doc_type_code_unique UNIQUE (code)
);
COMMENT ON TABLE  document_type IS 'Сведения о видах документов,
удостоверяющих личность физического лица';

CREATE TABLE IF NOT EXISTS Country(
id                 INTEGER  AUTO_INCREMENT    COMMENT 'Уникальный идентификатор',
name               VARCHAR(60)  NOT NULL      COMMENT 'Название страны',
code               VARCHAR(3)   NOT NULL      COMMENT 'Код страны',
CONSTRAINT country_id_pk PRIMARY KEY (id),
CONSTRAINT country_code_unique UNIQUE (code)
);
COMMENT ON TABLE country IS 'Справочник по странам';

CREATE INDEX IX_Organization_Name ON organization (name);

CREATE INDEX IX_Office_Organization_id ON office (organization_id);
ALTER TABLE office ADD CONSTRAINT office_org_fk FOREIGN KEY (organization_id) REFERENCES organization(id);

CREATE INDEX IX_Employee_Office_id ON employee (office_id);
ALTER TABLE employee ADD CONSTRAINT emp_office_fk FOREIGN KEY (office_id) REFERENCES office(id);

CREATE INDEX IX_Employee_Citizenship_name ON employee (citizenship_id);
ALTER TABLE employee ADD CONSTRAINT emp_country_fk FOREIGN KEY (citizenship_id) REFERENCES country(id);

ALTER TABLE document ADD CONSTRAINT doc_emp_fk FOREIGN KEY (employee_id) REFERENCES employee(id);

CREATE INDEX IX_Document_Document_name ON document (document_type_id);
ALTER TABLE document ADD  CONSTRAINT doc_doc_type_fk FOREIGN KEY (document_type_id) REFERENCES document_type(id)






