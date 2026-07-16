const fs = require('fs');
const path = require('path');
const { marked } = require('marked');

// Configure marked for better output
marked.setOptions({
  breaks: true,
  gfm: true,
  headerIds: true,
  mangle: false
});

// Read the markdown file
const markdownPath = path.join(__dirname, 'Notes.md');
const markdownContent = fs.readFileSync(markdownPath, 'utf-8');

// Convert markdown to HTML
const htmlContent = marked(markdownContent);

// Create the full HTML page with styling
const fullHtml = `<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Learning Notes</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/github-dark.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/highlight.min.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
            line-height: 1.6;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
        }

        h1 {
            color: #667eea;
            border-bottom: 3px solid #667eea;
            padding-bottom: 15px;
            margin-bottom: 30px;
            font-size: 2.5em;
        }

        h2 {
            color: #764ba2;
            margin-top: 40px;
            margin-bottom: 20px;
            font-size: 1.8em;
            border-left: 4px solid #764ba2;
            padding-left: 15px;
        }

        h3 {
            color: #667eea;
            margin-top: 25px;
            margin-bottom: 15px;
            font-size: 1.4em;
        }

        p {
            color: #333;
            margin-bottom: 15px;
        }

        ul, ol {
            margin-left: 30px;
            margin-bottom: 20px;
        }

        li {
            color: #555;
            margin-bottom: 8px;
        }

        code {
            background: #f5f5f5;
            padding: 2px 6px;
            border-radius: 4px;
            font-family: 'Courier New', monospace;
            font-size: 0.9em;
            color: #d63384;
        }

        pre {
            background: #2d2d2d;
            border-radius: 8px;
            padding: 20px;
            overflow-x: auto;
            margin: 20px 0;
        }

        pre code {
            background: none;
            padding: 0;
            color: #f8f8f2;
        }

        blockquote {
            border-left: 4px solid #667eea;
            padding-left: 20px;
            margin: 20px 0;
            color: #666;
            font-style: italic;
        }

        img {
            max-width: 100%;
            border-radius: 8px;
            margin: 20px 0;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background: #667eea;
            color: white;
        }

        tr:nth-child(even) {
            background: #f9f9f9;
        }

        hr {
            border: none;
            border-top: 2px solid #667eea;
            margin: 30px 0;
        }

        a {
            color: #667eea;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }

            h1 {
                font-size: 2em;
            }

            h2 {
                font-size: 1.5em;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        ${htmlContent}
    </div>

    <script>
        // Initialize syntax highlighting
        hljs.highlightAll();
    </script>
</body>
</html>`;

// Write the HTML file
const outputPath = path.join(__dirname, 'index.html');
fs.writeFileSync(outputPath, fullHtml, 'utf-8');

console.log('✅ Markdown converted to HTML successfully!');
console.log('📄 Output file: index.html');
