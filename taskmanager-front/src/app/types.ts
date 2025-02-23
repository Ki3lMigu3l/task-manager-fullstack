export interface Task {
    id: number
    title: string
    description: string
    category: string
    priority: "low" | "medium" | "high"
    status: string
    createdAt: string
}

export interface Filter {
    status: string
    category: string
    priority: string
}