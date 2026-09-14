-- 初期リストデータの登録
INSERT INTO lists (list_name) VALUES ('仕事');
INSERT INTO lists (list_name) VALUES ('プライベート');

-- 初期タスクデータの登録（仕事リスト(ID:1)に紐づくタスク）
INSERT INTO tasks (list_id, task_name) VALUES (1, '資料を作成する');
INSERT INTO tasks (list_id, task_name) VALUES (1, 'メールを返信する');

-- 初期タスクデータの登録（プライベートリスト(ID:2)に紐づくタスク）
INSERT INTO tasks (list_id, task_name) VALUES (2, '買い物に行く');
