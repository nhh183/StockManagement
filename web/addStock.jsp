<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Stock</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        h2 {
            text-align: center;
            margin-top: 30px;
            color: #2c3e50;
        }

        form {
            width: 400px;
            margin: 30px auto;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 12px rgba(0, 0, 0, 0.05);
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .form-buttons {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }

        .btn {
            padding: 10px 20px;
            border: none;
            color: white;
            background-color: #3498db;
            text-decoration: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
        }

        .btn:hover {
            background-color: #219150;
        }

        .btn.cancel {
            background-color: #7f8c8d;
        }

        .btn.cancel:hover {
            background-color: #636e72;
        }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Add New Stock</h2>
    <form action="MainController" method="post">
        <label>Ticker</label>
        <input type="text" name="ticker" required />

        <label>Name</label>
        <input type="text" name="name" required />

        <label>Sector</label>
        <input type="text" name="sector" required />

        <label>Price</label>
        <input type="number" step="0.01" min="0.01" name="price" required />

        <label>Status</label>
        <select name="status">
            <option value="true">Active</option>
            <option value="false">Inactive</option>
        </select>

        <input type="submit" class="btn" name="action" value="create" />
    </form>
</body>
</html>
