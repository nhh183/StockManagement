<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Stock</title>
    <style>
        form {
            width: 400px;
            margin: 30px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input, select {
            width: 100%;
            padding: 6px;
        }

        .btn {
            margin-top: 15px;
            padding: 8px 12px;
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
        <a href="welcome.jsp" class="btn">Cancel</a>
    </form>
</body>
</html>
