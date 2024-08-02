INSERT INTO Estudiante (id, nombre, apellido, profesion, contactos) VALUES
                                                                        (10000, 'Juan', 'Perez', 'Ingeniero', JSON_ARRAY('juan.perez@example.com')),
                                                                        (10001, 'Maria', 'Gomez', 'Doctora', JSON_ARRAY('maria.gomez@example.com')),
                                                                        (10002, 'Carlos', 'Rodriguez', 'Abogado', JSON_ARRAY('carlos.rodriguez@example.com')),
                                                                        (10003, 'Ana', 'Martinez', 'Arquitecta', JSON_ARRAY('ana.martinez@example.com')),
                                                                        (10004, 'Luis', 'Fernandez', 'Profesor', JSON_ARRAY('luis.fernandez@example.com')),
                                                                        (10005, 'Laura', 'Lopez', 'Enfermera', JSON_ARRAY('laura.lopez@example.com')),
                                                                        (10006, 'Jose', 'Garcia', 'Contador', JSON_ARRAY('jose.garcia@example.com')),
                                                                        (10007, 'Elena', 'Hernandez', 'Psicologa', JSON_ARRAY('elena.hernandez@example.com')),
                                                                        (10008, 'Miguel', 'Ramirez', 'Diseñador', JSON_ARRAY('miguel.ramirez@example.com')),
                                                                        (10009, 'Sofia', 'Gonzalez', 'Periodista', JSON_ARRAY('sofia.gonzalez@example.com'));

INSERT INTO Materia (id, nombre, profesor) VALUES
                                               (10000, 'Matematicas', 'Carlos Perez'),
                                               (10001, 'Fisica', 'Maria Lopez'),
                                               (10002, 'Quimica', 'Juan Hernandez'),
                                               (10003, 'Biologia', 'Ana Rodriguez'),
                                               (10004, 'Historia', 'Luis Gomez'),
                                               (10005, 'Geografia', 'Laura Fernandez'),
                                               (10006, 'Informatica', 'Jose Martinez'),
                                               (10007, 'Literatura', 'Elena Sanchez'),
                                               (10008, 'Arte', 'Miguel Ramirez'),
                                               (10009, 'Musica', 'Sofia Gonzalez');

INSERT INTO Docente (id, nombre, apellido, profesion, contactos) VALUES
                                                                     (10000, 'Carlos', 'Perez', 'Profesor de Matematicas', JSON_ARRAY('carlos.perez@unisabana.edu')),
                                                                     (10001, 'Maria', 'Lopez', 'Profesora de Fisica', JSON_ARRAY('maria.lopez@unisabana.edu')),
                                                                     (10002, 'Juan', 'Hernandez', 'Profesor de Quimica', JSON_ARRAY('juan.hernandez@unisabana.edu')),
                                                                     (10003, 'Ana', 'Rodriguez', 'Profesora de Biologia', JSON_ARRAY('ana.rodriguez@unisabana.edu')),
                                                                     (10004, 'Luis', 'Gomez', 'Profesor de Historia', JSON_ARRAY('luis.gomez@unisabana.edu')),
                                                                     (10005, 'Laura', 'Fernandez', 'Profesora de Geografia', JSON_ARRAY('laura.fernandez@unisabana.edu')),
                                                                     (10006, 'Jose', 'Martinez', 'Profesor de Informatica', JSON_ARRAY('jose.martinez@unisabana.edu')),
                                                                     (10007, 'Elena', 'Sanchez', 'Profesora de Literatura', JSON_ARRAY('elena.sanchez@unisabana.edu')),
                                                                     (10008, 'Miguel', 'Ramirez', 'Profesor de Arte', JSON_ARRAY('miguel.ramirez@unisabana.edu')),
                                                                     (10009, 'Sofia', 'Gonzalez', 'Profesora de Musica', JSON_ARRAY('sofia.gonzalez@unisabana.edu'));

INSERT INTO Hobby (id, nombre, horas_Por_Dias) VALUES
                                                 (10000, 'Futbol', '2'),
                                                 (10001, 'Baloncesto', '1.5'),
                                                 (10002, 'Natacion', '1'),
                                                 (10003, 'Lectura', '2'),
                                                 (10004, 'Pintura', '1'),
                                                 (10005, 'Musica', '1.5'),
                                                 (10006, 'Ciclismo', '2'),
                                                 (10007, 'Fotografia', '1.5'),
                                                 (10008, 'Ajedrez', '1'),
                                                 (10009, 'Yoga', '1.5');

INSERT INTO Notas (id, actividad, nota) VALUES
                                            (10000, 'Examen 1', 90),
                                            (10001, 'Tarea 1', 85),
                                            (10002, 'Proyecto', 88),
                                            (10003, 'Examen 2', 92),
                                            (10004, 'Tarea 2', 87),
                                            (10005, 'Laboratorio', 89),
                                            (10006, 'Examen Final', 91),
                                            (10007, 'Quizzes', 86),
                                            (10008, 'Participacion', 80),
                                            (10009, 'Presentacion', 84);

INSERT INTO Estudiante_Materia (estudiante_Id, materia_Id) VALUES
                                                            (10000, 10000),
                                                            (10001, 10001),
                                                            (10002, 10002),
                                                            (10003, 10003),
                                                            (10004, 10004),
                                                            (10005, 10005),
                                                            (10006, 10006),
                                                            (10007, 10007),
                                                            (10008, 10008),
                                                            (10009, 10009);

INSERT INTO Hobby_Estudiante (estudiante_Id, hobby_Id) VALUES
                                                        (10000, 10000),
                                                        (10001, 10001),
                                                        (10002, 10002),
                                                        (10003, 10003),
                                                        (10004, 10004),
                                                        (10005, 10005),
                                                        (10006, 10006),
                                                        (10007, 10007),
                                                        (10008, 10008),
                                                        (10009, 10009);


INSERT INTO Docente_Materias (docente_Id, materia_Id) VALUES
                                                       (10000, 10000),
                                                       (10001, 10001),
                                                       (10002, 10002),
                                                       (10003, 10003),
                                                       (10004, 10004),
                                                       (10005, 10005),
                                                       (10006, 10006),
                                                       (10007, 10007),
                                                       (10008, 10008),
                                                       (10009, 10009);
INSERT INTO Materia_Nota (materia_Id, notas_Id) VALUES
                                                 (10000, 10000),
                                                 (10000, 10002),
                                                 (10001, 10001),
                                                 (10002, 10002),
                                                 (10003, 10003),
                                                 (10004, 10004),
                                                 (10005, 10005),
                                                 (10006, 10006),
                                                 (10007, 10007),
                                                 (10008, 10008),
                                                 (10009, 10009);


INSERT INTO SEDE (id, locacion) VALUES
                                                 (10000, 'Bogota'),
                                                 (10001, 'Chia'),
                                                 (10002, 'Zipaquira'),
                                                 (10003, 'Tunja');

INSERT INTO Materia_Sede (materia_Id, sede_Id) VALUES
                                                 (10000, 10000),
                                                 (10001, 10001),
                                                 (10002, 10002),
                                                 (10003, 10003),
                                                 (10004, 10001),
                                                 (10005, 10002),
                                                 (10006, 10003),
                                                 (10007, 10000),
                                                 (10008, 10002),
                                                 (10009, 10001);

