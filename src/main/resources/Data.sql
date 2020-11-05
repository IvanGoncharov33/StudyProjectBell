INSERT INTO country (name, code) VALUES ('Российская Федерация', '643');
INSERT INTO country (name, code) VALUES ('Украина', '804');
INSERT INTO country (name, code) VALUES ('Французская Республика', '250');
INSERT INTO country (name, code) VALUES ('Королевство Швеция', '752');
INSERT INTO country (name, code) VALUES ('Чешская Республика', '203');

INSERT INTO document_type (name, code) VALUES
('Свидетельство о рождении', '03');
INSERT INTO document_type (name, code) VALUES
('Военный билет', '07');
INSERT INTO document_type (name, code) VALUES
('Временное удостоверение, выданное взамен военного билета ', '08');
INSERT INTO document_type (name, code) VALUES
('Паспорт иностранного гражданина', '10');
INSERT INTO document_type (name, code) VALUES
('Свидетельство о рассмотрении ходатайства о признании лица      
беженцем на территории Российской Федерации по существу', '11');
INSERT INTO document_type (name, code) VALUES
('Вид на жительство в Российской Федерации ', '12');
INSERT INTO document_type (name, code) VALUES
('Удостоверение беженца', '13');
INSERT INTO document_type (name, code) VALUES
('Разрешение на временное проживание в Российской Федерации', '15');
INSERT INTO document_type (name, code) VALUES
('Свидетельство о предоставлении временного убежища на территории
Российской Федерации','18');
INSERT INTO document_type (name, code) VALUES
('Паспорт гражданина Российской Федерации','21');
INSERT INTO document_type (name, code) VALUES                                                  
('Свидетельство о рождении, выданное уполномоченным органом      
иностранного государства','23');
INSERT INTO document_type (name, code) VALUES
('Удостоверение личности военнослужащего Российской Федерации','24');
INSERT INTO document_type (name, code) VALUES
('Иные документы','91');

INSERT INTO organization (name, version, full_name, inn, kpp, phone, address, is_active) VALUES
	('АО "Вертолеты России"', 0,  'Акционерное общество "Вертолеты России"',
				'7731559044', '770501001', '84956275545','Город Москва, улица Пионерская, дом 1', true);

INSERT INTO organization (name, version, full_name, inn, kpp, phone, address, is_active) VALUES
	('ПАО "ОАК"', 0, 'Публичное Акционерное Общество "Объединенная Авиастроительная Корпорация"',
								 '7708619320', '770505001','84953675545','Город Москва, улица Пионерская, дом 5', true);


INSERT INTO office (organization_id, version, name, phone, address, is_active) VALUES
	(1, 0, 'АО НЦВ Миль и Камов', '84996535199',
		'Московская обл., г. Люберцы, рп. Томилино, ул. Гаршина, д. 26/1', true);

INSERT INTO office (organization_id, version, name, phone, address, is_active) VALUES
	(2, 0, 'ОАК Закупки','74955877178', 'город Москва, Ленинградский проспект, 45Гк77', true);


INSERT INTO employee (office_id, version, first_name, second_name, middle_name, position, 
	citizenship_id, phone, is_identified) VALUES
		(1, 0, 'Виктор', 'Баринов','Викторович','Инженер', 1,'79998885522', true);

INSERT INTO employee (office_id, version, first_name, second_name, middle_name, position, 
	citizenship_id, phone, is_identified) VALUES
		(1, 0, 'Алексей', 'Негода','Андреевич','Сварщик', 1,'79998875522', true);

INSERT INTO employee (office_id,  version, first_name, second_name, middle_name, position, 
	citizenship_id, phone, is_identified) VALUES
		(1, 0, 'Александр', 'Пушкин','Сергеевич','Начальник отдела', 1,'79015475522', true);

INSERT INTO employee (office_id, version, first_name, second_name, middle_name, position, 
	citizenship_id, phone, is_identified) VALUES
		(2, 0, 'Владимир', 'Воевода','Карлович','Менеджер', 1,'79098885522', true);

INSERT INTO employee (office_id, version, first_name, second_name, middle_name, position, 
	citizenship_id, phone, is_identified) VALUES
		(2, 0,'Григорий', 'Луценко','Андреевич','Старший менеджер', 1,'79053473522', true);

INSERT INTO employee (office_id, version, first_name, second_name, middle_name, position, 
	citizenship_id, phone, is_identified) VALUES
		(2, 0, 'Александр', 'Гладышев','Николаевич','Директор по закупкам', 1,'79991113355', true);
		

INSERT INTO document (version, document_number, document_date, document_type_id, employee_id) VALUES
	 (0, '4411777222', TO_DATE('01-ЯНВ-2015','DD-MON-YYYY'), 10, 1);

INSERT INTO document (version, document_number, document_date, document_type_id, employee_id) VALUES
	 (0, '4311777222', TO_DATE('05-МАР-2011','DD-MON-YYYY'), 10, 2);

INSERT INTO document (version, document_number, document_date, document_type_id, employee_id) VALUES
	 (0, '1311555222', TO_DATE('26-ИЮН-2005','DD-MON-YYYY'), 10, 3);

INSERT INTO document (version, document_number, document_date, document_type_id, employee_id) VALUES
	 (0, '5511777232', TO_DATE('11-СЕН-2018','DD-MON-YYYY'), 10, 4);

INSERT INTO document (version, document_number, document_date, document_type_id, employee_id) VALUES
	 (0, '4311723222', TO_DATE('15-ОКТ-2001','DD-MON-YYYY'),10, 5);

INSERT INTO document (version, document_number, document_date, document_type_id, employee_id) VALUES
	 (0, '9911345222', TO_DATE('07-АВГ-2009','DD-MON-YYYY'),10, 6);


      

                         
      

                    

                                         

                            

                                                                                          

 