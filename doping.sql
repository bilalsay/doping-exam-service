create table student
(
    id      bigint generated by default as identity primary key,
    name    varchar(100) not null ,
    surname    varchar(100) not null ,
    number    varchar(100) not null
);

create unique index ix_unique_student_number ON student (number);

create table exam
(
    id      bigint generated by default as identity primary key,
    name    varchar(200) not null
);

create table question
(
    id      bigint generated by default as identity primary key,
    number    integer,
    text       text not null,
    exam_id    bigint not null ,
    selections   json not null,
    correct_selection    varchar(200) not null
);

alter table question
    add constraint fk_question_to_exam
        foreign key (exam_id) references exam;

create table student_exam
(
    id      bigint generated by default as identity primary key,
    student_id    bigint not null ,
    exam_id    bigint not null ,
    answers    json
);

create unique index ix_unique_student_exam_ids ON student_exam (exam_id, student_id);

alter table student_exam
    add constraint fk_student_exam_to_exam
        foreign key (exam_id) references exam;

alter table student_exam
    add constraint fk_student_exam_to_student
        foreign key (student_id) references student;
