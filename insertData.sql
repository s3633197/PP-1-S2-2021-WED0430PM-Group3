
INSERT INTO project1.account (
    password,
    email,
    create_time,
    last_login_time,
    role_id
);
VALUES (
    "$2a$10$CUeJdx/Xr8R9vqy9uCdLje6Pl6WaH9e1SSuDErNYwmwI3CsmWx/UG",
    "dummydata@test.com",
    "2020-11-14",
    "2021-09-14",
    2
);


INSERT INTO project1.company (
    company_name,
    start_up_date,
    industry,
    owner,
    account_id
)
VALUES
(
    "Apple",
    "2000-09-13",
    "IT",
    "Steve",
    1
),

(
    "Dragonfly Environmental",
    "2021-09-13",
    "Accounting",
    "andre",
    4
);

INSERT INTO project1.post (
    title,
    description,
    industry,
    position,
    address,
    educational_background,
    employment_type,
    min_salary,
    max_salary,
    company_id,
    post_time,
    update_time
)
VALUES (
    "Finance Manager",
    "Dragonfly Environmental are specialists in ecological restoration of wetlands, riparian zones and saltmarshes and environmental management of a wide range of terrestrial ecosystems, being recognised leaders in their field.",
    "Accounting",
    "Financial Managers & Controllers",
    "Sydney Parramatta & Western Suburbs",
    "BACHELOR",
    "Full-time",
    40,
    80,
    1,
    "2021-09-14",
    "2021-09-14"
),
(
    "Financial Accountant",
    "KickAss Products is on a mission to find a Financial Accountant who is a weapon! Someone who is driven and enthusiastic about numbers!",
    "Accounting",
    "Financial Accounting & Reporting",
    "Australian Direct Pty Ltd & Sunshine Coast",
    "BACHELOR",
    "Full-time",
    30,
    39,
    1,
    "2021-09-14",
    "2021-09-14"
),
(
    "Management Accountant (Budget)",
    "Permanent full-time position 35 hours per week, 19-day fortnight
$87,928.88 - $101,998.00 + 10% superannuation

We are looking forward… and seeing a bright future.

Wingecarribee Shire Council is situated in the magnificent Southern Highlands with a balance of pristine natural scenery and a rich diversity of lifestyle choices from its historic past to modern amenities all at your doorstep.",
    "Accounting",
    "Management Accounting & Budgeting",
    "Wingecarribee Shire Council & Southern Highlands & Tablelands",
    "BACHELOR",
    "Full-time",
    42,
    60,
    1,
    "2021-09-20",
    "2021-09-20"
),
(
    "Accountant | Tech start-up! | $80-85k + super Robert Half",
    "An exciting opportunity has arisen for a dynamic and enthusiastic Accountant to join a rapidly growing fintech business, based in Melbourne's CBD.
    
    Apply Today

Please send your resume by clicking on the apply button or for further information, contact our Melbourne office on 03 9691 36 31.",
    "Accounting",
    "Financial Accounting & Reporting",
    "Melbourne CBD & Inner Suburbs",
    "BACHELOR",
    "Full-time",
    30,
    32.5,
    1,
    "2021-09-20",
    "2021-09-20"
),
(
    "Accounts Officer OSAN Ability",
    "
At OSAN Ability, we live by our mission statement; “Quality Care with Compassion”. We work by a strong moral and ethical code to ensure the highest level of care and support is delivered to our Participants, Families, and Friends. 

Our dynamic team is seeking to employ a dedicated, passionate, caring, and enthusiastic Accounts Officer to join our office team",
    "Accounting",
    "Accounts Officers/Clerks",
    "SydneyNorth West & Hills District",
    "BACHELOR",
    "Full-time",
    28,
    35,
    1,
    "2021-09-15",
    "2021-09-15"
),
(
    "Financial Accountant",
    "KickAss Products is on a mission to find a Financial Accountant who is a weapon! Someone who is driven and enthusiastic about numbers!",
    "Accounting",
    "Financial Accounting & Reporting",
    "Wingecarribee Shire Council & Southern Highlands & Tablelands",
    "BACHELOR",
    "Full-time",
    42,
    50,
    1,
    "2021-09-20",
    "2021-09-20"
),
(
    "Senior Accountant",
    "Chartered Accounting practice in South Yarra with a sophisticated client base seeking strong and technically confident Senior Accountant to work closely with a Senior Partner.",
    "Accounting",
    "Business Services & Corporate Advisory",
    "Melbourne CBD & Inner Suburbs",
    "Diploma",
    "Full-time",
    20,
    20,
    1,
    "2021-09-15",
    "2021-09-15"
),
(
    "Assistant Accountant",
    "We are a family owned and operated business in its 3rd generation. We are hard working with strong beliefs, and extremely proud of the service and products we deliver to our customers. With over 50 years of joint experience and knowledge, we have established long lasting contacts and relationships with Australian Farmers and an invaluable knowledge of the Victorian fruit & vegetable Industry.",
    "Accounting",
    "Assistant Accountants",
    "Melbourne Western Suburbs",
    "BACHELOR",
    "Full-time",
    20,
    20,
    1,
    "2021-09-15",
    "2021-09-15"
),
(
    "Accounts Officer/Bookkeeper JJ communications",
    "JJ Communications is a thriving Telecommunications company that has an amazing work culture.  Providing specialised electrical and communication services to modern high technology industries, we have seen rapid growth in the past 20 months, now employing over 45 staff in many different states.",
    "Accounting",
    "Bookkeeping & Small Practice Accounting",
    "Melbourne Eastern Suburbs",
    "BACHELOR",
    "PARIME",
    25,
    30,
    1,
    "2021-09-20",
    "2021-09-20"
);

INSERT INTO project1.post (
    title,
    description,
    industry,
    position,
    address,
    educational_background,
    employment_type,
    min_salary,
    max_salary,
    company_id,
    post_time,
    update_time
)
VALUES (
    ".net Software Engineer nDeva Recruitment",
    "Do you want to do work that helps the community? 

Often times when working for bigger companies you’re subject to picking up various stories and building code around it. However, every now and then you get a role where you can be highly involved in the technical design process in a modern stack. A ‘difference making’ opportunity.",
    "Information & Communication Technology",
    "Information & Communication Technology",
    "Melbourne CBD & Inner Suburbs",
    "BACHELOR",
    "Full-time",
    40,
    42,
    5,
    "2021-09-20",
    "2021-09-20"   
),
(
    "Software Developer",
    "As our software solutions are constantly evolving, we currently have an exciting new opportunity for an experienced and driven Software Developer to join our team. ",
    "Information & Communication Technology",
    "Developers/Programmers",
    "Melbourne Eastern Suburbs",
    "BACHELOR",
    "Full-time",
    40,
    45,
    5,
    "2021-09-18",
    "2021-09-18"   
),
(
    "IT Skills Development Officer for Seniors | Manningham City Council",
    "Do you want to do work that helps the community? 

Often times when working for bigger companies you’re subject to picking up various stories and building code around it. However, every now and then you get a role where you can be highly involved in the technical design process in a modern stack. A ‘difference making’ opportunity.",
    "Information & Communication Technology",
    "Help Desk & IT Support",
    "Melbourne",
    "Diploma",
    "Part-time",
    0,
    0,
    5,
    "2021-09-20",
    "2021-09-20"   
),
(
    "IT Skills Development Officer for Seniors | Manningham City Council",
    "Part time position 19 hours per week, Temporary (fixed term) until 30 June 2022, $81,412 + 10% Superannuation (pro rata), Based at Doncaster",
    "Information & Communication Technology",
    "Help Desk & IT Support",
    "Melbourne",
    "DIPLOMA",
    "Part-time",
    0,
    0,
    5,
    "2021-09-20",
    "2021-09-20"   
),
(
    "IT Service Desk Team Leader | Neami National Limited",
    "Working closely with the Manager IT Customer Service, the IT Service Desk Team Leader will supervise and develop the IT Customer Service Desk, including appropriate delegation and monitoring of responsibilities, performance management and analysis and implementation of training requirements.",
    "Information & Communication Technology",
    "Management",
    "Melbourne Northern Suburbs",
    "BACHELOR",
    "Part-time",
    45,
    52,
    5,
    "2021-09-20",
    "2021-09-20"   
),
(
    "IT Desktop Support",
    "Strong knowledge of PC hardware and related peripherals, Knowledge of wired and wireless network technologies and Broadband services, Demonstrated skills in imaging PCs and installation of hardware and related peripherals",
    "Information & Communication Technology",
    "Help Desk & IT Support",
    "Melbourne Northern Suburbs",
    "DIPLOMA",
    "Contract",
    0,
    0,
    5,
    "2021-09-16",
    "2021-09-16"   
),
(
    "Onsite Computer Technician Work from Home - COVID-19 Safe Role",
    "Earn between $1000-$3000/week, Work from home and hours flexibility, Fantastic new challenges delivered each day that use your many years of accumulated technical skills and experience.",
    "Information & Communication Technology",
    "Help Desk & IT Support",
    "Melbourne CBD & Inner Suburbs",
    "DIPLOMA",
    "Contract",
    35,
    50,
    5,
    "2021-09-18",
    "2021-09-18"   
),
(
    "IT Student / Helpdesk Support - Student Internship Program 2022",
    "Our Student Internship Program offers exclusive opportunities for the brightest students to gain invaluable experience in your field of study and related industries.",
    "Information & Communication Technology",
    "Help Desk & IT Support",
    "Melbourne CBD & Inner Suburbs",
    "BACHELOR",
    "UNKOWN",
    0,
    0,
    5,
    "2021-09-18",
    "2021-09-18"   
),
(
    "Junior JavaScript ReactJS nodejs developer",
    "Here's your big break! We're looking for a fresh graduate / junior developer with anywhere from 0 to 3 years experience. Must know JavaScript.",
    "Information & Communication Technology",
    "Developers/Programmers",
    "Melbourne CBD & Inner Suburbs",
    "BACHELOR",
    "Full-time",
    40,
    40,
    5,
    "2021-09-18",
    "2021-09-18"   
),
(
    "Graduate Software Developer",
    "We are currently seeking an energetic and dedicated Graduate Software Developer to join our team based in Heidelberg, Victoria. The Graduate will be supported by an experienced Software Development Manager in the enhancement and further development of existing software packages and apps.",
    "Information & Communication Technology",
    "Developers/Programmers",
    "Melbourne Northern Suburbs",
    "BACHELOR",
    "Full-time",
    0,
    0,
    5,
    "2021-09-18",
    "2021-09-18"   
),
(
    "Graduate Software Developer",
    "We are currently seeking an energetic and dedicated Graduate Software Developer to join our team based in Heidelberg, Victoria. The Graduate will be supported by an experienced Software Development Manager in the enhancement and further development of existing software packages and apps.",
    "Information & Communication Technology",
    "Developers/Programmers",
    "Melbourne Northern Suburbs",
    "BACHELOR",
    "Full-time",
    0,
    0,
    5,
    "2021-09-18",
    "2021-09-18"   
);


INSERT INTO project1.post (
    title,
    description,
    industry,
    position,
    address,
    educational_background,
    employment_type,
    min_salary,
    max_salary,
    company_id,
    post_time,
    update_time
)
VALUES (
    "ARCHITECTURAL DRAFTSPERSON & BUILDING DESIGNER",
    "BB Design Group Pty Ltd, is a consulting building design office in the northern suburbs of Melbourne with a reputation for high quality, superior and innovative design for industrial, commercial & residential projects.",
    "Design & Architecture",
    "Architectural Drafting",
    "Melbourne Northern Suburbs",
    "BACHELOR",
    "Full-time",
    0,
    0,
    5,
    "2021-09-18",
    "2021-09-18"   
),
(
    "Architectural Graduate - Design Focus",
    "This highly renowned, mid-scale architectural studio has a focus on boutique multi-res, commercial, and public projects.",
    "Design & Architecture",
    "Architecture",
    "Melbourne CBD & Inner Suburbs",
    "BACHELOR",
    "Full-time",
    0,
    0,
    5,
    "2021-09-18",
    "2021-09-18"   
);