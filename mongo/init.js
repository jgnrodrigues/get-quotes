db.createUser(
    {
        user: "quotes_user",
        pwd: "quotes_password",
        roles: [
            {
                role: "readWrite",
                db: "quotes"
            }
        ]
    }
);
db.createCollection("quotes");