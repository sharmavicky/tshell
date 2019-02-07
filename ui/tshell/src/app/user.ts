export class User {
    employeeId: string;
    name: string;
    email: string;
    role: Role;
    password: string;
    signupOtp: string;

    constructor(employeeId, signupOtp, name, email, role, password) {
        this.employeeId = employeeId;
        this.signupOtp = signupOtp;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;

    }

}


export class Role {
    id: number;
    name: string;
    constructor(name) {
        this.name = name;
    }

}