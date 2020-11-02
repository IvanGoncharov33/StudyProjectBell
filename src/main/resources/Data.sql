INSERT INTO country (name, code) VALUES ('Российская Федерация', '643');

INSERT INTO document_type (name, code) VALUES ('	
Свидетельство о рождении', '03');
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
('Разрешение на временное проживание в Российской Федерации    ', '15');
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
				'7731559044', '770501001', '84956275545','Город Москва, улица Пионерская, дом 1','Y');

INSERT INTO organization (name, version, full_name, inn, kpp, phone, address, is_active) VALUES
	('ПАО "ОАК"', 0, 'Публичное Акционерное Общество "Объединенная Авиастроительная Корпорация"',
								 '7708619320', '770505001','84953675545','Город Москва, улица Пионерская, дом 5','Y');

INSERT INTO office (organization_id, version, name, phone, address, is_active) VALUES
	(1, 0, 'АО НЦВ Миль и Камов', '84996535199',
		'Московская обл., г. Люберцы, рп. Томилино, ул. Гаршина, д. 26/1', 'Y');

INSERT INTO office (organization_id, version, name, phone, address, is_active) VALUES
	(2, 0, 'ОАК Закупки','74955877178', 'город Москва, Ленинградский проспект, 45Гк77','Y');

INSERT INTO employee (office_id, version, first_name, second_name, middle_name, position, 
	citizenship_name, phone, is_identified) VALUES
		(1, 0, 'Виктор', 'Баринов','Викторович','Инженер','Российская Федерация','79998885522','Y');

INSERT INTO employee (office_id, version, first_name, second_name, middle_name, position, 
	citizenship_name, phone, is_identified) VALUES
		(1, 0, 'Алексей', 'Негода','Андреевич','Сварщик','Российская Федерация','79998875522','Y');

INSERT INTO employee (office_id,  version, first_name, second_name, middle_name, position, 
	citizenship_name, phone, is_identified) VALUES
		(1, 0, 'Александр', 'Пушкин','Сергеевич','Начальник отдела','Российская Федерация','79015475522','Y');

INSERT INTO employee (office_id, version, first_name, second_name, middle_name, position, 
	citizenship_name, phone, is_identified) VALUES
		(2, 0, 'Владимир', 'Воевода','Карлович','Менеджер','Российская Федерация','79098885522','Y');

INSERT INTO employee (office_id, version, first_name, second_name, middle_name, position, 
	citizenship_name, phone, is_identified) VALUES
		(2, 0,'Григорий', 'Луценко','Андреевич','Старший менеджер','Российская Федерация','79053473522','Y');

INSERT INTO employee (office_id, version, first_name, second_name, middle_name, position, 
	citizenship_name, phone, is_identified) VALUES
		(2, 0, 'Александр', 'Гладышев','Николаевич','Директор по закупкам','Российская Федерация','79991113355','Y');

INSERT INTO document (version, document_name, document_number, document_date, employee_id) VALUES
	 (0, 'Паспорт гражданина Российской Федерации', '4411777222', TO_DATE('01-ЯНВ-2015','DD-MON-YYYY'),1);

INSERT INTO document (version, document_name, document_number, document_date, employee_id) VALUES
	 (0, 'Паспорт гражданина Российской Федерации', '4311777222', TO_DATE('05-МАР-2011','DD-MON-YYYY'),2);

INSERT INTO document (version, document_name, document_number, document_date, employee_id) VALUES
	 (0, 'Паспорт гражданина Российской Федерации', '1311555222', TO_DATE('26-ИЮН-2005','DD-MON-YYYY'),3);

INSERT INTO document (version, document_name, document_number, document_date, employee_id) VALUES
	 (0, 'Паспорт гражданина Российской Федерации', '5511777232', TO_DATE('11-СЕН-2018','DD-MON-YYYY'),4);

INSERT INTO document (version, document_name, document_number, document_date, employee_id) VALUES
	 (0, 'Паспорт гражданина Российской Федерации', '4311723222', TO_DATE('15-ОКТ-2001','DD-MON-YYYY'),5);

INSERT INTO document (document_name, document_number, document_date, employee_id) VALUES
	 (0, 'Паспорт гражданина Российской Федерации', '9911345222', TO_DATE('07-АВГ-2009','DD-MON-YYYY'),6);


      

                         
      

                    

                                         

                            

                                                                                          

 