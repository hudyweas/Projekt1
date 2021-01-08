
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `answers` (
                           `id` int(11) NOT NULL,
                           `content` text NOT NULL,
                           `isCorrect` int(1) NOT NULL,
                           `question_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `answers` (`id`, `content`, `isCorrect`, `question_id`) VALUES
(1, 'tak', 0, 1),
(2, 'nie', 1, 1),
(3, 'jest bardzo plaska', 0, 1),
(4, 'zdecydowanie nie jest', 1, 1),
(5, 'tak', 1, 2),
(6, 'nie', 0, 2),
(7, '2', 1, 5),
(8, '-2', 1, 5),
(9, '1/2', 0, 5),
(11, 'tak', 1, 3),
(12, 'nie', 0, 3),
(13, 'wynosi 3', 0, 3),
(14, 'wynosi 1/2', 0, 3),
(15, 'tak', 1, 4),
(16, 'nie', 0, 4);

CREATE TABLE `questions` (
                             `id` int(11) NOT NULL,
                             `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `questions` (`id`, `content`) VALUES
(1, 'Czy ziemia jest płaska?'),
(2, 'Czy rok przystepny jest co 4 lata?'),
(3, 'Czy 1+1 = 2'),
(4, 'Czy po a w alfabecie jest b?'),
(5, 'Jeśli a^2 = 4 ile wynosi a');

ALTER TABLE `answers`
    ADD PRIMARY KEY (`id`),
  ADD KEY `id_question` (`question_id`);

ALTER TABLE `questions`
    ADD PRIMARY KEY (`id`);

ALTER TABLE `answers`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

ALTER TABLE `questions`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `answers`
    ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`);
