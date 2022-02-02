INSERT learning.users
VALUES (null, 35, 'Admin', 'Admins', '$2a$10$DK2Yd6NWHU9cGcJqOjbpBuV1RSF51HfEncxiD7XXLNTVYfPiR/KMi', 'ADMIN@ADMIN'),
       (null, 23, 'Sasha', 'Sidorov', '$2a$10$dHV9OjXcGqp9aP/tXEM30OAa9JXZU3zm7X5iTvSIodCRnUZO870sy', '1@1'),
       (null, 33,'Sveta', 'Petrov', '$2a$10$7NZp/u/OmOWSKJtNs5lPX.BnwZUZiOxgW9Wuu43U/A1omD9muD1/K', '2@2'),
       (null, 24,'Ivan', 'Oveec', '$2a$10$Ejx2lY7NyxdtacT6MJDF2uHM8r97R9mJb8bcKucDaoeKVNNr7tRnK', '3@3'),
       (null, 55,'Sergei', 'Kirovec', '$2a$10$xp.ncI2d11tp32vOvBc7A.DhgSU4AuWhHndOc4HTObGWchMGP1uzS', '4@4'),
       (null, 88,'Masha', 'Maheev', '$2a$10$W/.c9t/FBx51MKXZiAXW2OAls9JoKU/CrxnGJe7rRgrxgSvQfE0.e', '5@5'),
       (null, 26,'Andrei', 'Kudrovec', '$2a$10$uzyxyKbTtLrEAaDZfu/rCejcest4H6RPwkKxr528t/cSJzOJ2x6QC', '6@6');

INSERT learning.roles
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

INSERT learning.users_roles
VALUES (1, 2),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1);